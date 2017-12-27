
package kata6.view;

import kata6.controller.Attribute;
import kata6.model.Histogram;
import kata6.model.Mail;
import java.util.List;

public class MailHistogramBuilder<T> {
    private final List<T> items;

    public MailHistogramBuilder(List<T> items) {
        this.items = items;
    }

    public <A>Histogram<A> buil(Attribute<T,A> attribute){
        Histogram<A> histo = new Histogram<>();
        for (T item : items) {
            A value = attribute.get(item);
            histo.increment(value);
        }
        
        return histo;
    }
    
}

