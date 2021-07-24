package cn.gb.demo.spring5.annotationVerDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "favC")
public class FavC {

    @Value(value = "小林さん")
    private String cNm;

    @Override
    public String toString() {
        return "FavC{" +
                "cNm='" + cNm + '\'' +
                '}';
    }
}
