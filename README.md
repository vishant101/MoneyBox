# MoneyBox
A mini version of the Moneybox app that will allow existing users to login, check their account and add money to their moneybox. The app is built with Rx Kotlin and mvvm architecture with android databindings. It also features dependancy injection via dagger and networking with retrofit. Room is used for the database.

## Getting Started
1. Download and install android studio

2. Clone the repo:
- `git clone https://github.com/vishant101/MoneyBox.git`

3. Snyc gradle 

4. Run the app on an emulator or device

5. Login using the following details

|  Username          | Password         |
| ------------- | ------------- |
| androidtest@moneyboxapp.com  | P455word12  |

## Screenshots
| Login | Account Overview | Individual Account |
|------|---------|-----|
| <img src="https://github.com/vishant101/MoneyBox/blob/master/images/Login.png/" width="275" alt="Login" title="Login" /> | <img src="https://github.com/vishant101/MoneyBox/blob/master/images/AccountOverview.png" width="275" alt="AccountOverview" title="AccountOverview" /> | <img src="https://github.com/vishant101/MoneyBox/blob/master/images/IndividualAccount.png" width="275" alt="IndividualAccount" title="IndividualAccount" /> 

## Architecture
The app was built with an mvvm architecture. This keeps UI code simple and free of app logic in order to make it easier to manage and test. Android databindings were uses so that the viewmodels have no refrence to the UI elemetents and thus can be tested independantly of the UI itself. An RX mindset was used to keep the app scalable going forward. Depenandancy injection was done via dagger so that components can be tested indepenantly without strong references. For networking I used retrofit, a strong yet lightwieght client. To manage databetween the screens a room database was used. This keeps the data synced between screens.

## API Usage
This a brief summary of the api endpoints in the moneybox sandbox environment.

### Headers and Authentication
#### Base URL & Test User
The base URL for the moneybox sandbox environment is `https://api-test01.moneyboxapp.com/`.
You can log into test your app using the following user:

|  Username          | Password         |
| ------------- | ------------- |
| androidtest@moneyboxapp.com  | P455word12  |

#### Headers

In order to make requests https must be used and the following headers must be included in each request.

|  Key | Value |
| ------------- | ------------- |
| AppId  | 3a97b932a9d449c981b595  |
| Content-Type  | application/json  |
| appVersion | 5.10.0 |
| apiVersion | 3.0.0 |

### API Calls
#### Login
```
POST /users/login
{
  "Email": "androidtest@moneyboxapp.com",
  "Password": "P455word12",
  "Idfa": "ANYTHING"
}
```
Sample json response
```
"Session": {
        "BearerToken": "TsMWRkbrcu3NGrpf84gi2+pg0iOMVymyKklmkY0oI84=",
        "ExternalSessionId": "4ff0eab7-7d3f-40c5-b87b-68d4a4961983", -- not used
        "SessionExternalId": "4ff0eab7-7d3f-40c5-b87b-68d4a4961983", -- not used
        "ExpiryInSeconds": 0 -- not used
    }
```
After obtaining a bearer token an Authorization header must be provided for all other endpoints along with the headers listed above (Note: The BearerToken has a sliding expiration of 5 mins).

|  Key          | Value         |
| ------------- | ------------- |
| Authorization  | Bearer TsMWRkbrcu3NGrpf84gi2+pg0iOMVymyKklmkY0oI84=  |

#### Investor Products
Provides product and account information for a user.
```
GET /investorproducts
```
#### One off payments
Adds a one off amount to the users moneybox.
```
POST /oneoffpayments
{
  "Amount": 20,
  "InvestorProductId": 3230 ------> The InvestorProductId from /investorproducts endpoint
}
```
