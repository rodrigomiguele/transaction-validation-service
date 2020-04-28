package rmiguele.transaction.validation.resource;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.service.PersonService;
import rmiguele.transaction.validation.vo.CreatePersonVO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {

    @Inject
    PersonService personService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(CreatePersonVO createPersonVO) {
        var command = new CreatePersonCommand();
        command.setPersonSituation(createPersonVO.getPersonSituation());
        personService.createPerson(command);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons(){
        return personService.getPersons();
    }
}
