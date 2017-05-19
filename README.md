# Event Driven Applications with Spring Boot

This projects tries to capture various options you have when dealing with Event Driven Spring Boot applications.
The follwing Spring Technologies are being used:
- Spring Boot
- Spring Cloud Stream Rabbit
- Spring Data JPA

These examples contain various different ways to model and deal with events:
- Complete aggregates / entities in the events
- REST Resource URLs in events
- Partial parsing / handling of events in consumers
- Events as Atom Feeds

## Prerequisites
- A basic installation of RabbitMQ must be installed and running (rabbitmq-server)

## How to run and install the example
Compile each module with mvn clean install and start them with mvn spring:run

## URLs and Ports
Each of the modules is it's own Spring Boot Application which can be accessed as follows:

<table>
    <tr>
        <th>Name</th>
        <th>Application / Enpoint Type</th>
        <th>URL</th>
    </tr>
    <tr>
        <td>Application Process</td>
        <td>9000</td>
        <td>http://localhost:9000</td>
    </tr>
    <tr>
        <td>Credit Application</td>
        <td>9001</td>
        <td>http://localhost:9001/credit-application</td>
    </tr>
    <tr>
        <td>Customer</td>
        <td>9002</td>
        <td>http://localhost:9002/customer and http://localhost:9002/customer/feed</td>
    </tr>
    <tr>
        <td>Scoring</td>
        <td>9003</td>
        <td>No UI</td>
    </tr>
     <tr>
        <td>CreditDecision</td>
        <td>9004</td>
        <td>http://localhost:9004/credit-decision and http://localhost:9004/credit-decision/feed</td>
    </tr>
      
    
</table>

## Messaging Infrastructure & Domain Events

### Public Events

#### CreditApplicationNumberGeneratedEvent
Source: application-process

Persisted in source: no

Consumers:
- credit-application
- credit-decision

Topic: CreditApplicationNumberGeneratedTopic


#### CreditApplicationEnteredEvent
Source: credit-application

Persisted in source: yes in its own Table via JPA

Consumers:
- application-process
- credit-decision

Topic: CreditApplicationEnteredTopic


#### CustomerCreatedEvent
Source: customer

Persisted in source: no

Consumers:
- application-process
- credit-decision

Topic: CustomerCreatedTopic

#### ScoringPositiveEvent
Source: scoring

Persisted in source: no

Consumers:
- application-process
- credit-decision

Topic: ScoringPositiveTopic

#### ScoringNegativeEvent
Source: scoring

Persisted in source: no

Consumers:
- application-process
- credit-decision

Topic: ScoringNegativeTopic

#### ApplicationDeclinedEvent
Source: credit-decision

Persisted in source: not as an event

Consumers:
- application-process

Topic: ApplicationDeclinedTopic

### Internal Events

#### Credit-Application
- CreditDetailsEnteredEvent
- FinancialSituationEnteredEvent

Both events are stored
Source: credit-application
Storage: Own Table via JPA


### Feeds

#### Customer Feed
Url: http://localhost:9002/customer/feed

Contains URLs to Customer Resources

#### Credit Decision Feed
Url: http://localhost:9004/credit-decision/feed

Contains Application Numbers that have been confirmed


