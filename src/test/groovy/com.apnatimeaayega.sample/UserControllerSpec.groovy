package com.apnatimeaayega.sample

import com.apnatimeaayega.sample.controllers.UserController
import com.apnatimeaayega.sample.domain.User
import com.apnatimeaayega.sample.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class UserControllerSpec extends Specification {

    private ObjectMapper objectMapper = new ObjectMapper()

    def userRepository = Mock(UserRepository)

    @Subject
    def api = new UserController(userRepository)

    def mockMvc = MockMvcBuilders.standaloneSetup(api).build()

    @Unroll
    def "add user"() {
        given:
        User user = new User.UserBuilder().id(1).firstName("first").lastName("last").age(10).build()
        userRepository.save(user) >> user

        when:
        def response = mockMvc.perform(post('/user/add').contentType(MediaType.APPLICATION_JSON).content(toJson(user)))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()
        response.contentAsString == "user saved"
    }

    @Unroll
    def "get user by id"() {
        given:
        User user = new User.UserBuilder().id(1).firstName("first").lastName("last").age(10).build()
        userRepository.findById(1) >> Optional.of(user)

        when:
        def response = mockMvc.perform(get('/user/1').accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()
    }

    @Unroll
    def "get all users"() {
        given:
        def users = []
        users << new User.UserBuilder().id(1).firstName("first").lastName("last").age(10).build()
        users << new User.UserBuilder().id(2).firstName("foo").lastName("bar").age(20).build()
        userRepository.findAll() >> users

        when:
        def response = mockMvc.perform(get('/user/all').accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .response

        then:
        response.status == HttpStatus.OK.value()
        with(objectMapper.readValue(response.contentAsString, List)) {
            it.get(index).get("id") == id
            it.get(index).get("firstName") == first
            it.get(index).get("lastName") == last
            it.get(index).get("age") == age
        }

        where:
        index | id | first   | last   | age
        0     | 1  | "first" | "last" | 10
        1     | 2  | "foo"   | "bar"  | 20
    }
}
