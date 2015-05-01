import com.gravity.goose.{Configuration, Goose}

val g =  new Goose(new Configuration).extractContent("http://www.qfmzambia.com/2015/04/26/saunders-reminds-pf-about-constitution/")

g.getTopImage().getImageSrc