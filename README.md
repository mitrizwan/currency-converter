# currency-converter
currency-converter-application will expose below list of APIs.
This application talks to NoSQL database - MongoDB, this can very well talk to external DB provided we need to feed DB specific
properties in the application.properties file.

At the startup the aaplication will feed the database.
And it holds below documents-

Currency(id=5e9e060ca7197f5f8d53d0e8, currency=INR, country=INDIA, rate=76.5285)
Currency(id=5e9e060da7197f5f8d53d0e9, currency=SGD, country=SINGAPORE, rate=1.42317)
Currency(id=5e9e060da7197f5f8d53d0ea, currency=GBP, country=BRITAIN, rate=0.8004)
Currency(id=5e9e060da7197f5f8d53d0eb, currency=EUR, country=EUROPE, rate=0.920154)
Currency(id=5e9e060da7197f5f8d53d0ec, currency=CAD, country=CANADA, rate=1.40397)
Currency(id=5e9e060da7197f5f8d53d0ed, currency=HKD, country=HONG KONG, rate=7.75)




API #1.  It will give list of all avaialble currencies fetched from mongo database:

GET http://localhost:8080/currency-converter/getCurrencies

[
    "INR",
    "SGD",
    "GBP",
    "EUR",
    "CAD",
    "HKD"
]

API #2. It will give list of all currencies along with the countries and rate associated with them keeping USD as base.

GET http://localhost:8080/currency-converter/getCurrenciesRates


[
    {
        "currency": "INR",
        "country": "INDIA",
        "rate": 76.5285
    },
    {
        "currency": "SGD",
        "country": "SINGAPORE",
        "rate": 1.42317
    },
    {
        "currency": "GBP",
        "country": "BRITAIN",
        "rate": 0.8004
    },
    {
        "currency": "EUR",
        "country": "EUROPE",
        "rate": 0.920154
    },
    {
        "currency": "CAD",
        "country": "CANADA",
        "rate": 1.40397
    },
    {
        "currency": "HKD",
        "country": "HONG KONG",
        "rate": 7.75
    }
]

API #3. It will give currency details of a particular country supplied in Query param.

GET http://localhost:8080/currency-converter/getCurrencyRate?country=INDIA


{
    "currency": "INR",
    "country": "INDIA",
    "rate": 76.5285
}

API #4. It will give the converted euivalent amount for the supplied currencies.

POST http://localhost:8080/currency-converter/getCurrencyConversionRate

{
    "currencyFrom": "INR",
    "currencyTo": "HKD",
    "date": "2017-04-20",
    "amount": 5,
    "netAmount": 0.5063473085190485
}

