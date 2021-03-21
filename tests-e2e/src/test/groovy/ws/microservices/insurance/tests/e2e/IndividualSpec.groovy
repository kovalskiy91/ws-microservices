package ws.microservices.insurance.tests.e2e

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class IndividualSpec extends Specification {
    @Shared
    def servicePath = "http://localhost:8080"
    @Shared
    def individualPath = "/api/v1/individuals"
    @Shared
    def client = new RESTClient(servicePath)

    def "should create new individual"() {
        given: "new individual"
        def individual = ['firstName': 'John', 'lastName': "Doe"]

        when: "individual creation is executed"
        def response = client.post(
                path: individualPath,
                requestContentType: "application/json",
                body: individual
        )

        then: "individual is created"
        response.status == 201
        and: "individual image is returned"
        def createdIndividual = response.data
        with(createdIndividual) {
            id ==~ '[0-9]+'
            firstName == individual.firstName
            lastName == individual.lastName
        }
        and: "individual location is returned"
        response.headers.location == "$servicePath$individualPath/${createdIndividual.id}"
    }

    def "should find individual by id when it exists"() {
        given: "existing individual"
        def individual = ['firstName': 'Jane', 'lastName': "Doe"]
        def createResponse = client.post(
                path: individualPath,
                requestContentType: "application/json",
                body: individual
        )
        def individualId = createResponse.data.id

        when:
        def response = client.get(path: "$individualPath/$individualId")

        then: "individual is found"
        response.status == 200
        and: "response contains individual details"
        def foundIndividual = response.data
        with(foundIndividual) {
            id == individualId
            firstName == individual.firstName
            lastName == individual.lastName
        }
    }

}
