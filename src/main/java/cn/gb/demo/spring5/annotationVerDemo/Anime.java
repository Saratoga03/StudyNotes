package cn.gb.demo.spring5.annotationVerDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component(value = "anime")
/*@Controller
@Service
@Repository*/
public class Anime {

    @Value(value = "小林さんちのメイドラゴンS")
    private String name;

    @Autowired
    private FavC favC1;

    @Autowired
    @Qualifier(value = "favC")
    private FavC favC2;

    @Override
    public String toString() {
        return "Anime{" +
                "name='" + name + '\'' +
                ", favC1=" + favC1 +
                ", favC2=" + favC2 +
                '}';
    }
}
