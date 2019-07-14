package com.apnatimeaayega.sample

import com.apnatimeaayega.sample.controllers.HelloController
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ContextConfiguration
@WebMvcTest(controllers = HelloController)
class HelloControllerSpec extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    @Unroll
    def "say hello"() {
        when:
        def response = mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()
        response.contentAsString == "Hello!"
    }

    @Unroll
    def "say hello user"() {
        when:
        def response = mockMvc.perform(get("/hello/param").param("name", key).accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()

        and:
        with(objectMapper.readValue(response.contentAsString, Map)) {
            it.id == 10
            it.name == value
        }

        where:
        key   | value
        ""    | "World!"
        "foo" | "foo"
    }
}
