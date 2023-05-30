package org.api.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.endpoints.userEndpoints;
import org.api.payloads.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class userTest {
    Faker faker;
    User userPayload;
   // public Logger logger;
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs

       // logger=  LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser() {
     //   logger.info("******Creating user*********");
        Response response = userEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
       // logger.info("******Created user*********");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
    //    logger.info("******Getting user*********");

        Response res = userEndpoints.getUser(this.userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
     //   logger.info("******Got user*********");
    }

    @Test(priority = 3)
    public void testUpdateUserName() {
      //  logger.info("******Updating user*********");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response res = userEndpoints.updateUser(this.userPayload.getUsername(), userPayload);//passing two parameter check user payload
        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200);
        //checking data after update
        Response afterUpdate_user = userEndpoints.getUser(this.userPayload.getUsername());
        Assert.assertEquals(afterUpdate_user.getStatusCode(), 200);
     //   logger.info("******Updated user*********");
    }

    @Test(priority = 4)
    public void deleteUser() {
       // logger.info("******deleting user*********");
        Response res = userEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(res.getStatusCode(), 200);
       // logger.info("******deleted user*********");
    }
}
