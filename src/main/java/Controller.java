import com.google.gson.Gson;
import model.Person;
import response.StandardResponse;
import response.StatusResponse;
import services.PersonServicesImpl;
import services.interfaces.PersonServices;

import static spark.Spark.get;
import static spark.Spark.post;

public class Controller {
    public static void main(String[] args) {
        final PersonServices services = new PersonServicesImpl();
        post("/person", (request, response) -> {
            response.type("application/json");
            Person person = new Gson().fromJson(request.body(), Person.class);
            services.postPerson(person);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/person", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(services.getAllPeople())));
        });

        get("/person/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(services.getPersonById(request.params(":id")))));
        });
    }
}
