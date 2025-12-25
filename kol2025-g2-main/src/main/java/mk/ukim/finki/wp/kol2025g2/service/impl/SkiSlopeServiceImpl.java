package mk.ukim.finki.wp.kol2025g2.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.kol2025g2.model.SkiResort;
import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import mk.ukim.finki.wp.kol2025g2.model.exceptions.InvalidSkiResortIdException;
import mk.ukim.finki.wp.kol2025g2.model.exceptions.InvalidSkiSlopeIdException;
import mk.ukim.finki.wp.kol2025g2.repository.SkiResortRepository;
import mk.ukim.finki.wp.kol2025g2.repository.SkiSlopeRepository;
import mk.ukim.finki.wp.kol2025g2.service.SkiResortService;
import mk.ukim.finki.wp.kol2025g2.service.SkiSlopeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.kol2025g2.service.FieldFilterSpecification.*;

@Service
@RequiredArgsConstructor
public class SkiSlopeServiceImpl implements SkiSlopeService {
    private final SkiSlopeRepository skiSlopeRepository;
    private final SkiResortRepository skiResortRepository;
    @Override
    public List<SkiSlope> listAll() {
        return skiSlopeRepository.findAll();
    }

    @Override
    public SkiSlope findById(Long id) {
        return skiSlopeRepository.findById(id).orElseThrow(InvalidSkiSlopeIdException::new);
    }

    @Override
    public SkiSlope create(String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiResort resort=skiResortRepository.findById(skiResort).orElseThrow(InvalidSkiResortIdException::new);
        return skiSlopeRepository.save(new SkiSlope(name,length,difficulty,resort));
    }

    @Override
    public SkiSlope update(Long id, String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiSlope skiSlope=findById(id);
        SkiResort resort=skiResortRepository.findById(skiResort).orElseThrow(InvalidSkiResortIdException::new);
        skiSlope.setName(name);
        skiSlope.setLength(length);
        skiSlope.setDifficulty(difficulty);
        skiSlope.setSkiResort(resort);
        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public SkiSlope delete(Long id) {
        SkiSlope skiSlope=findById(id);
        skiSlopeRepository.deleteById(id);
        return skiSlope;
    }

    @Override
    public SkiSlope close(Long id) {
        SkiSlope skiSlope=findById(id);
        skiSlope.setClosed(true);
        return skiSlopeRepository.save(skiSlope);
    }

    @Override
    public Page<SkiSlope> findPage(String name, Integer length, SlopeDifficulty difficulty, Long skiResort, int pageNum, int pageSize) {
        Specification<SkiSlope> specification = Specification.allOf(
               filterContainsText(SkiSlope.class,"name",name),  //fieldot ke mora da vidime kako ni se vika vo skiSlope clasata atributot
                greaterThan(SkiSlope.class,"length",length),
                filterEqualsV(SkiSlope.class,"difficulty",difficulty),
                filterEquals(SkiSlope.class,"skiResort.id",skiResort)//bidejki skiResort ni e objekt a barame id mozhe da napraime samo skiResort.id vo field
        );
        return skiSlopeRepository.findAll(
                specification,
                PageRequest.of(pageNum , pageSize));

    }
}
