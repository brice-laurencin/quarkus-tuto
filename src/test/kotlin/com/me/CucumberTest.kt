package com.me

import io.cucumber.java.en.*
import io.mockk.every
import io.quarkiverse.cucumber.CucumberQuarkusTest
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import io.restassured.RestAssured
import io.restassured.response.ValidatableResponse
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import java.time.ZonedDateTime

@QuarkusTest
@TestProfile(com.me.TestProfile::class)
class CucumberTest: CucumberQuarkusTest() {

    @InjectMock
    lateinit var clockService: ClockService

    lateinit var then: ValidatableResponse

    @Given("current time is {string}")
    fun `current time`(dateString: String) {
        every { clockService.currentTime() } returns ZonedDateTime.parse(dateString)
    }

    @When("I asked for current time")
    fun `I asked for current time`() {
        then = RestAssured.given().get("/").then()
    }

    @Then("I get {string}")
    fun `I get`(expectedString: String) {
        val actual = ZonedDateTime.parse(then.extract().asString())
        val expected = ZonedDateTime.parse(expectedString)

        MatcherAssert.assertThat(actual, Matchers.`is`(expected))
    }

}

class TestProfile : QuarkusTestProfile {
    override fun getConfigProfile(): String {
        return "test"
    }
}
