# Item catalog API

# API de Produtos

A item manegemant api.

## Get all Items

Return all user items.

### Request:

`GET /item`

### Response:

```json
[
  {
    "id": 1,
    "ownerName": "User1",
    "name": "My Item",
    "unitValue": 10.0
    "amount": 5,
    "totalValue": 50.0,
    "createdAt": ,
    "updatedAt": 
  },
  {
    "id": 2,
    "ownerName": "User1",
    "name": "My item 2",
    "unitValue": 17.0
    "amount": 2,
    "totalValue": 34.0,
    "createdAt": ,
    "updatedAt": 
  }
]
```
## Get Item by ID

### Request:

`GET /item/{id}`

### Response: 

``` json
 {
    "id": 1,
    "ownerName": "User1",
    "name": "My Item",
    "unitValue": 10.0
    "amount": 5,
    "totalValue": 50.0,
    "createdAt": ,
    "updatedAt": 
 }
```

## Create Item

### Request:

`POST /item`

#### Body:
``` json
 {
    "name": "My Item3",
    "unitValue": 5.0
    "amount": 10
 }
```

### Response:

``` json
 "Item created"
```
