# Bit Coin service
    BitCoin service will help to provide history of bitcoin based of start date and end date
    It can provide result for any currency , provided exchange rate value.

# Hexagonal architecture
    We have used hexagonal architecture. This will help to keep the domain clean and independent of the framework and infrastructure. 
    As frameworks and infrastructures change frequently, this architecture ensures our domain/business functionality remains unchanged.

# Online Mode
    It uses count desk apis to fetch bitcoin prices in online mode.
    for online mode set offline property to false
    
```yaml
    count-desk:
        url: https://api.coindesk.com/
        offline: false
```

# Offline Mode
    It uses in memory cache to fetch bitcoin prices in offline mode.
    for online mode set offline property to false

```yaml
    count-desk:
        url: https://api.coindesk.com/
        offline: true
```
