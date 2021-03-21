package ws.microservices.insurance.tests.e2e

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class PolicySpec extends Specification {
    @Shared
    def servicePath = "http://localhost:8080"
    @Shared
    def individualPath = "/api/v1/individuals"
    @Shared
    def organizationPath = "/api/v1/organizations"
    @Shared
    def proposalPath = "/api/v1/proposals"
    @Shared
    def policyPath = "/api/v1/policies"
    @Shared
    def client = new RESTClient(servicePath)

    def "should create new policy"() {
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
        and: "existing proposal for organization"
        def proposal = ['planHolderId': organizationId]
        def proposalCreateResponse = client.post(
                path: proposalPath,
                requestContentType: "application/json",
                body: proposal
        )
        def proposalId = proposalCreateResponse.data.id
        and: "new policy for proposal and organization"
        def policy = ['proposalId': proposalId, 'planHolderId': organizationId]

        when: 'policy creation is executed'
        def policyCreateResponse = client.post(
                path: policyPath,
                requestContentType: "application/json",
                body: policy
        )

        then: "policy is created"
        policyCreateResponse.status == 201
        and: "policy image is returned"
        def createdPolicy = policyCreateResponse.data
        with(createdPolicy) {
            id ==~ '[0-9]+'
            proposalId == policy.proposalId
            planHolderId == policy.planHolderId
        }
        and: "policy location is returned"
        policyCreateResponse.headers.location == "$servicePath$policyPath/${createdPolicy.id}"
    }
}
