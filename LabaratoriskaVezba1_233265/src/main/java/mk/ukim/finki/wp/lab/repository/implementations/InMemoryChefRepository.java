//package mk.ukim.finki.wp.lab.repository.implementations;
//
//import mk.ukim.finki.wp.vebprogramiranje.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Chef;
//import mk.ukim.finki.wp.lab.repository.interfaces.ChefRepository;
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
//        if(chef!=null){
//            DataHolder.chefs.removeIf(c->c.getId().equals(chef.getId()));
//            DataHolder.chefs.add(chef);
//            return chef;
//        }
//        throw new IllegalArgumentException("Chef cannot be null!");
//    }
//}
