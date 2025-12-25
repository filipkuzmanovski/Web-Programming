package mk.ukim.finki.wp.kol2025g2.repository;

import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import org.springframework.stereotype.Repository;
//zavisno od koj repository ke koristi pagination ke mora da iskopirame JpaSpecificationRepository od aud i ke mora da iskopirame FieldFilterSpecification vo service slojot
//vo productServiceImpl ni se naogja implementacija na ova za da ne uchish na pamet debil eden
@Repository
public interface SkiSlopeRepository extends JpaSpecificationRepository<SkiSlope,Long> {
}
