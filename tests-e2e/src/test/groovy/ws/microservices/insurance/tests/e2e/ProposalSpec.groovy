package ws.microservices.insurance.tests.e2e

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class ProposalSpec extends Specification {
    @Shared
    def servicePath = "http://localhost:8080"
    @Shared
    def individualPath = "/api/v1/individuals"
    @Shared
    def organizationPath = "/api/v1/organizations"
    @Shared
    def proposalPath = "/api/v1/proposals"
    @Shared
    def client = new RESTClient(servicePath)

    def "should create new proposal"() {
        given: "existing individual"
        def individual = ['firstName': 'Jane', 'lastName': "Doe"]
        def individualCreateResponse = client.post(
                path: individualPath,
                requestContentType: "application/json",
                body: individual
        )
        def individualId = individualCreateResponse.data.id
        and: "individual is employee"
        def employee = ['individualId': individualId]
        and: "existing organization with employee"
        def organization = [
                'title'    : 'TestOrg1',
                'employees': [employee]
        ]
        def organizationCreateResponse = client.post(
                path: organizationPath,
                requestContentType: "application/json",
                body: organization
        )
        def organizationId = organizationCreateResponse.data.id
        and: "new proposal for organization"
        def proposal = ['planHolderId': organizationId]

        when: 'proposal creation is executed'
        def proposalCreateResponse = client.post(
                path: proposalPath,
                requestContentType: "application/json",
                body: proposal
        )


        then: "proposal is created"
        proposalCreateResponse.status == 201
        and: "proposal image is returned"
        def createdProposal = proposalCreateResponse.data
        with(createdProposal) {
            id ==~ '[0-9]+'
            planHolderId == proposal.planHolderId
        }
//        and: "proposal location is returned"
//        proposalCreateResponse.headers.location == "$servicePath$proposalPath/${createdProposal.id}"
    }
}
