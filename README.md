# Event Driven Applications with Spring Boot


## Prerequisites
- A basic installation of RabbitMQ must be installed and running (rabbitmq-server)

## How to run and install the example

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
        <td>http://localhost:9000/</td>
    </tr>
    <tr>
        <td>Credit Application</td>
        <td>9001</td>
        <td>http://localhost:9001/credit-application</td>
    </tr>
    <tr>
        <td>Credit Application</td>
        <td>9002</td>
        <td>http://localhost:9002/customer</td>
    </tr>
    
</table>

## Messageing Infrastructure & Domain Events

### Public Events

#### CreditApplicationNumberGeneratedEvent
Source: application-process

Persisted in source: no

Consumers:
- credit-application

Topic: CreditApplicationNumberGeneratedTopic


#### CreditApplicationEnteredEvent
Source: credit-application

Persisted in source: yes in its own Table via JPA

Consumers:

Topic: CreditApplicationEnteredTopic


### Internal Events

#### Credit-Application
- CreditDetailsEnteredEvent
- FinancialSituationEnteredEvent

Both events are stored
Source: credit-application
Storage: Own Table via JPA
