package org.vaadin.viritin;

import junit.framework.Assert;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Test;
import org.vaadin.viritin.fields.LazyComboBox;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.testdomain.Person;
import org.vaadin.viritin.testdomain.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matti ät vaadin.com
 */
public class LazyComboBoxUsage {

    MutableObject<Person> selectedValue = new MutableObject();

    @Test
    public void testTypedSelect() {

        final LazyService service = new LazyService();
        
        // This is naturally much cleaner with Java 8
        LazyComboBox<Person> cb = new LazyComboBox(Person.class);
        cb.loadFrom(new LazyComboBox.FilterablePagingProvider() {

            @Override
            public List findEntities(int firstRow, String filter) {
                return service.findPersons(filter, firstRow, LazyList.DEFAULT_PAGE_SIZE);
            }
        }, new LazyComboBox.FilterableCountProvider() {

            @Override
            public int size(String filter) {
                return service.countPersons(filter);
            }
        
        });
        
        cb.addMValueChangeListener(new MValueChangeListener<Person>() {

            private static final long serialVersionUID = -659661563076688180L;

            @Override
            public void valueChange(MValueChangeEvent<Person> event) {
                selectedValue.setValue(event.getValue());
            }
        });
        final Person fourth = service.all.get(4);
        cb.setValue(fourth);
        
        Assert.assertEquals(fourth, selectedValue.getValue());
        

    }

    /**
     * stub to simulate a service like jpa facade
     */
    public static class LazyService {

        List<Person> all = Service.getListOfPersons(100);

        public List<Person> findPersons(String filter, int startIndex, int maxResults) {
            List<Person> list = findPersons(filter);
            int last = startIndex + maxResults;
            if(last > list.size()) {
                last = list.size();
            }
            return new ArrayList<>(list.subList(startIndex, last));
        }
        
        private List<Person> findPersons(String filter) {
            if(filter == null || filter.isEmpty()) {
                return all;
            }
            ArrayList<Person> filtered = new ArrayList();
            for (Person p : all) {
                if(p.toString().toLowerCase().contains(filter)) {
                    filtered.add(p);
                }
            }
            return filtered;
        }

        public int countPersons(String filter) {
            return findPersons(filter).size();
        }

    }

}
