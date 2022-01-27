package remind.me.coding.error;

import javax.persistence.EntityNotFoundException;

public class RemindmeEntityNotFoundException extends EntityNotFoundException {
    public RemindmeEntityNotFoundException(String message){
        super(message);
    }
}
