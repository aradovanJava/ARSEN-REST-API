package arsenwebdemo.arsenweb.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import arsenwebdemo.arsenweb.enumeration.City;
import arsenwebdemo.arsenweb.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AddressRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @BeforeAll
  static void init() {
  }

  @Test
  @Order(1)
  void should_return_all_addresses_status_ok_size_4() throws Exception {
    this.mockMvc.perform(get("/address")).
        andExpect(status().isOk()).
        andExpect(content().contentType(MediaType.APPLICATION_JSON)).
        andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  @Order(2)
  void should_return_address_by_id_status_ok() throws Exception {
    int addressIdToFind = 4; // Replace with a valid ID in your database

    mockMvc
        .perform(get("/address/" + addressIdToFind))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(addressIdToFind))
        .andExpect(jsonPath("$.street").value("Tratinska")) // Replace with expected values
        .andExpect(jsonPath("$.city").value(City.ZAGREB.name()))
        .andExpect(jsonPath("$.houseNumber").value(50)); // Replace with expected values
  }

  @Test
  @Order(3)
  void should_return_not_found_for_nonexistent_address() throws Exception {
    int nonexistentAddressId = 999; // Use an ID that does not exist in your database

    mockMvc
        .perform(get("/address/" + nonexistentAddressId))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @Order(4)
  void should_save_a_new_address_status_created() throws Exception {

    Address newAddress = new Address("Ilica", City.ZAGREB, 1);

    mockMvc
        .perform(
            post("/address")
                .content(objectMapper.writeValueAsString(newAddress))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.street").value(newAddress.getStreet()))
        .andExpect(jsonPath("$.city").value(newAddress.getCity().name()))
        .andExpect(jsonPath("$.houseNumber").value(newAddress.getHouseNumber()));
  }


  @Test
  @Order(5)
  void should_update_existing_address_status_ok() throws Exception {

    Address updatedAddress = new Address("Updated Street", City.ZAGREB, 123);
    int addressIdToUpdate = 3; // Replace with a valid ID in your database

    mockMvc
        .perform(
            put("/address/" + addressIdToUpdate)
                .content(objectMapper.writeValueAsString(updatedAddress))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.street").value(updatedAddress.getStreet()))
        .andExpect(jsonPath("$.city").value(updatedAddress.getCity().name()))
        .andExpect(jsonPath("$.houseNumber").value(updatedAddress.getHouseNumber()));
  }

  @Test
  @Order(6)
  void should_return_not_found_for_nonexistent_address_while_updating() throws Exception {

    Address updatedAddress = new Address("Updated Street", City.ZAGREB, 123);
    int nonexistentAddressId = 999; // Use an ID that does not exist in your database

    mockMvc
        .perform(
            put("/address/" + nonexistentAddressId)
                .content(objectMapper.writeValueAsString(updatedAddress))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isNotFound());
  }



  @Test
  @Order(7)
  void should_delete_existing_address_status_no_content() throws Exception {
    int addressIdToDelete = 4; // Replace with a valid ID in your database

    mockMvc
        .perform(delete("/address/" + addressIdToDelete))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  @Order(8)
  void should_return_not_found_for_nonexistent_address_while_deleting() throws Exception {
    int nonexistentAddressId = 999; // Use an ID that does not exist in your database

    mockMvc
        .perform(delete("/address/" + nonexistentAddressId))
        .andDo(print())
        .andExpect(status().isNotFound());
  }


}