//package mk.ukim.finki.wp.labvezba.repository.implementations;
//
//import mk.ukim.finki.wp.labvezba.bootstrap.DataHolder;
//import mk.ukim.finki.wp.labvezba.model.Chef;
//import mk.ukim.finki.wp.labvezba.repository.interfaces.ChefRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryChefRepository implements ChefRepository {
//
//    @Override
//    public List<Chef> findAll() {
//        return DataHolder.chefs;
//    }
//
//    @Override
//    public Optional<Chef> findById(Long id) {
//        return DataHolder.chefs.stream().filter(c->c.getId().equals(id)).findFirst();
//    }
//
//    @Override
//    public Chef save(Chef chef) {
//        DataHolder.chefs.removeIf(c->c.getId().equals(chef.getId()));
//        DataHolder.chefs.add(chef);
//        return chef;
//    }
//}
