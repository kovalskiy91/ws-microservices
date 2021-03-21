package ws.microservices.insurance.tests.e2e

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class OrganizationSpec extends Specification {
    @Shared
    def servicePath = "http://localhost:8080"
    @Shared
    def individualPath = "/api/v1/individuals"
    @Shared
    def organizationPath = "/api/v1/organizations"
    @Shared
    def client = new RESTClient(servicePath)

    def "should create new organization"() {
        given: "new organization"
        def organization = ['title': 'TestOrg1']

        when: "organization creation is executed"
        def createResponse = client.post(
                path: organizationPath,
                requestContentType: "application/json",
                body: organization
        )

        then: "organization is created"
        createResponse.status == 201
        and: "organization image is returned"
        def createdOrganization = createResponse.data
        with(createdOrganization) {
            id ==~ '[0-9]+'
            title == organization.title
        }
        and: "organization location is returned"
        createResponse.headers.location == "$servicePath$organizationPath/${createdOrganization.id}"
    }

    def "should find organization by id when it exists"() {
        given: "existing organization"
        def organization = ['title': 'TestOrg2']
        def createResponse = client.post(
                path: organizationPath,
                requestContentType: "application/json",
                body: organization
        )
        def organizationId = createResponse.data.id

        when:
        def response = client.get(path: "$organizationPath/$organizationId")

        then: "organization is found"
        response.status == 200
        and: "response contains organization details"
        def foundOrganization = response.data
        with(foundOrganization) {
            id == organizationId
            title == organization.title
        }
    }

    def "should create new organization with employee when individual exists"() {
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
        and: "new organization with employee"
        def organization = [
                'title'    : 'TestOrg3',
                'employees': [employee]
        ]

        when: "organization creation is executed"
        def organizationCreateResponse = client.post(
                path: organizationPath,
                requestContentType: "application/json",
                body: organization
        )

        then: "organization is created"
        organizationCreateResponse.status == 201
        and: "created organization has employee"
        def createdOrganization = organizationCreateResponse.data
        with(createdOrganization) {
            employees[0].id ==~ '[0-9]+'
            employees[0].individualId == employee.individualId
        }
    }

}
