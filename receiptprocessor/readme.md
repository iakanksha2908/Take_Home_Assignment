#                                        ***Receipt Processor API***


 ## This README provides information about the Receipt Processor API, including its available endpoints, request and response formats, and example curl commands for interacting with the API.

 ### cmd to create the jar file - mvn clean install

 ## Endpoints
 
 ### 1. Process a Receipt

 ### Endpoint: /receipts/process
 Method: POST
 Description: Processes a receipt and saves it in the database. Returns the generated id for the receipt.
 Request Body:
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}

### curl command

### curl -Uri "http://localhost:9090/receipts/process" `
-Method POST `
-Headers @{"Content-Type" = "application/json"} `
-Body '{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}'

 response sample 
{
  "id": "de49f583-d1c4-4c03-8d95-e4cbd921bbbe"
}

### 2. Get Receipt Points
 Endpoint: /receipts/{id}/points
 Method: GET
 Description: Retrieves the points for a given receipt based on the id

### curl -Uri "http://localhost:9090/receipts/de49f583-d1c4-4c03-8d95-e4cbd921bbbe/points" `
-Method GET

 Sample response
{
  "points": 109
}


### Docker command used to build the image
docker build -t springboot-receipt-processor-docker.jar . 

### Docker command used to run the image
docker run -p 9090:8080 springboot-receipt-processor-docker.jar
