package main

import (
	"context"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"io/ioutil"
	"log"
	"net/http"
)

type Payment struct {
	Id string `json:"id"`
	UserId string `json:"userid"`
	MentorId string `json:"mentorid"`
	TId string `json:"tid"`
	Amount string `json:"amount"`
}

func main()  {

	myRouter := mux.NewRouter().StrictSlash(true)
	myRouter.HandleFunc("/payment", welcome)
	myRouter.HandleFunc("/payment/createPayment", createPayment).Methods("POST")
	myRouter.HandleFunc("/payment/getAllPayment", getAllPayment)	//default GET
	myRouter.HandleFunc("/payment/{id}", getPayment)	//default GET
	myRouter.HandleFunc("/payment/deletePayment/{id}", deletePayment).Methods("DELETE")
	http.ListenAndServe(":8965", myRouter)
}

func welcome(w http.ResponseWriter, _ *http.Request)  {
	fmt.Fprintf(w, "Welcome to Payment portal")
}

func deletePayment(w http.ResponseWriter, req *http.Request) {

	//Getting path variable
	vars := mux.Vars(req)
	idval := vars["id"]

	//MONGODB Connection
	clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")
	collection := client.Database("mydb").Collection("payments")

	filter := bson.M{"id": idval}

	_ , err = collection.DeleteOne(context.TODO(), filter)

	json.NewEncoder(w).Encode("Payment Deleted")
}

func getPayment(w http.ResponseWriter, req *http.Request) {

	//Getting path variable
	vars := mux.Vars(req)
	idval := vars["id"]

	//MONGODB Connection
	clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")
	collection := client.Database("mydb").Collection("payments")

	findOptions := options.Find()
	findOptions.SetLimit(1)

	filter := bson.M{"id": idval}

	cur, err := collection.Find(context.TODO(), filter, findOptions)

	if err != nil {
		log.Fatal(err)
	}

	var elem Payment

	for cur.Next(context.TODO()) {
		err := cur.Decode(&elem)

		if err != nil {
			log.Fatal(err)
		}
	}

	if err := cur.Err(); err != nil {
		log.Fatal(err)
	}

	cur.Close(context.TODO())

	fmt.Println("Sending Payment in Response")
	json.NewEncoder(w).Encode(elem)
}

func getAllPayment(w http.ResponseWriter, _ *http.Request) {

	//MONGODB Connection
	clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")
	collection := client.Database("mydb").Collection("payments")

	findOptions := options.Find()
	findOptions.SetLimit(0)
	//findOptions.SetSkip(2)
	//findOptions.SetSort(bson.D{{"id",-1}})

	var payments []Payment

	//Filters
	filter := bson.M{}
	//filter := bson.M{"id": bson.M{"$gt": 20}}

	cur, err := collection.Find(context.TODO(), filter, findOptions)

	if err != nil {
		log.Fatal(err)
	}

	for cur.Next(context.TODO()) {
		var elem Payment
		err := cur.Decode(&elem)

		if err != nil {
			log.Fatal(err)
		}

		//fmt.Printf("Found:", elem)
		payments = append(payments, elem)
	}

	if err := cur.Err(); err != nil {
		log.Fatal(err)
	}

	cur.Close(context.TODO())

	fmt.Println("Sending All Payments in Response")
	json.NewEncoder(w).Encode(payments)

}

func createPayment(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Creating Payment......")

	//Mongo Connection
	clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
	client, err := mongo.Connect(context.TODO(), clientOptions)

	if err != nil {
		log.Fatal(err)
	}

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")
	collection := client.Database("mydb").Collection("payments")

	//Getting JSON object
	requestBody, err := ioutil.ReadAll(req.Body)
	var ppayment Payment
	json.Unmarshal(requestBody, &ppayment)

	//Inserting into DB
	insertResult, err := collection.InsertOne(context.TODO(), ppayment)

	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Received Object", ppayment)
	fmt.Println("Payment Created: ", insertResult.InsertedID)

	json.NewEncoder(w).Encode(insertResult.InsertedID.(primitive.ObjectID).Hex())
}