package edu.sjsu.cmpe.locationbased.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.locationbased.domain.Property;
import edu.sjsu.cmpe.locationbased.dto.PropertyDto;
import edu.sjsu.cmpe.locationbased.dto.LinkDto;
import edu.sjsu.cmpe.locationbased.dto.LinksDto;
import edu.sjsu.cmpe.locationbased.repository.PropertyRepositoryInterface;

@Path("/property/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyResource {
    /** bookRepository instance */
    private final PropertyRepositoryInterface propertyRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public PropertyResource(PropertyRepositoryInterface propertyRepository) {
	this.propertyRepository = propertyRepository;
    }

    @GET
    @Path("{id}")
    @Timed(name = "view-property")
    public PropertyDto getPropertyByIsbn(@PathParam("id") LongParam id) {
    	Property property = propertyRepository.getPropertyByID(id.get());
    	PropertyDto propertyResponse = new PropertyDto(property);
    	propertyResponse.addLink(new LinkDto("view-property", "/property/" + property.getId(),"GET"));
    	propertyResponse.addLink(new LinkDto("update-property", "/property/" + property.getId(),	"PUT"));
    	//propertyResponse.addLink(new LinkDto("delete-book",	"/books/" + book.getIsbn(), "DELETE"));
    	//propertyResponse.addLink(new LinkDto("create reviews","/books/" + book.getIsbn()+ "/reviews", "POST"));
	/*if(property.getReviews().size()!=0){
	propertyResponse.addLink(new LinkDto("view-all-reviews","/books/" + property.getIsbn()+"/reviews", "GET"));
	}*/
	// add more links

	return propertyResponse;
    }
    @POST
    @Timed(name = "create-property")
    public Response createProperty(Property request) {
	// Store the new book in the BookRepository so that we can retrieve it.
    	Property savedProperty = propertyRepository.saveProperty(request);

	String location = "/property/" + savedProperty.getId();
	LinksDto propertyResponse = new LinksDto();
	propertyResponse.addLink(new LinkDto("view-property", location, "GET"));
	propertyResponse.addLink(new LinkDto("update-property", location, "PUT"));
	//bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	//bookResponse.addLink(new LinkDto("create-review","/books/"+ savedProperty.getIsbn() + "/reviews", "POST"));
	
	// Add other links if needed

	return Response.status(201).entity(propertyResponse).build();
    }
    


	/*
    @DELETE
	@Path("/{id}")
    @Timed(name = "delete-book")
    public Response deletePropertyByIsbn(@PathParam("id") LongParam id) {
    	if(bookRepository.deleteBookByID(id.get()) == true){
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("create-book", "/books", "POST"));
    	return Response.status(200).entity(bookResponse).build();
    	}
    	else
    		return Response.status(409).build();
    	
    } 
    */
   
    @PUT
	@Path("{id}")
    @Timed(name = "update-property")
    public Response updatePropertyByIsbn(@PathParam("id") LongParam id,@QueryParam("address") String address) {
    	 if(propertyRepository.updatePropertyByID(id.get(), address)){
    		String location = "/property/"+id;
        	LinksDto propertyResponse = new LinksDto();
        	Property property = propertyRepository.getPropertyByID(id.get());
        	propertyResponse.addLink(new LinkDto("view-property", location, "GET"));
        	propertyResponse.addLink(new LinkDto("update-property", location, "PUT"));
        
            
        	return Response.status(200).entity(propertyResponse).build();	
    	}
    	else
    		return Response.status(409).build(); 	
    
    /*
    @PUT
 	@Path("{id}")
     @Timed(name = "update-book")
     public Response updateBookAddressByIsbn(@PathParam("id") LongParam id,@QueryParam("address") String address) {
     	if(bookRepository.updateBookByID(id.get(), address)){
     		String location = "/property/";
         	LinksDto bookResponse = new LinksDto();
         	Property book = propertyRepository.getPropertyByID(id.get());
         	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
         	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
         	//bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
         	//bookResponse.addLink(new LinkDto("create-review", "/books/"+id+"reviews", "POST"));
         	//if(book.getReviews().size()!=0){
         	//	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"/reviews", "GET"));
         		}
         	return Response.status(200).entity(bookResponse).build();	
     	}
     	else
     		return Response.status(409).build();		
     } */
/**    
*    @POST
*	@Path("/{id}/reviews")
*	@Timed(name = "create-review")
*	public Response createReviews(@PathParam("id") Long id,@Valid Reviews review) {
*
*		Reviews reviewResponse =  BookRepository.createReviews(id, review);
*		Book book=new Book();
*		BookDto bookCreateReviewResponse = new BookDto(book);
*		
*			bookCreateReviewResponse.addLink(new LinkDto("view-review", "/books/"+id+"/reviews/"+reviewResponse.getId() ,
*					"GET"));
*
*			return Response.status(201).entity(bookCreateReviewResponse).build();
*		}
*/		
}
}
