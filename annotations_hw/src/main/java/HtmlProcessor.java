import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes(value = {"HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private final HtmlCreator htmlCreator;

    public HtmlProcessor() {
        htmlCreator = new HtmlCreator();
    }

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : annotatedElements) {

            Form form = new Form(element.getAnnotation(HtmlForm.class).action(),
                    element.getAnnotation(HtmlForm.class).method());

            List<? extends Element> enclosedElements = element.getEnclosedElements();

            for (Element elem : enclosedElements) {
                if (elem.getAnnotation(HtmlInput.class) != null) {
                    form.getInputs().add(Input.builder()
                                    .type(elem.getAnnotation(HtmlInput.class).type())
                                    .name(elem.getAnnotation(HtmlInput.class).name())
                                    .placeholder(elem.getAnnotation(HtmlInput.class).placeholder())
                                    .build());
                }
            }

            // получаем путь с class-файлам
            String path = HtmlForm.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            // создадим путь к html-файлу
            // User.class -> User.html
            path = path.substring(1) + element.getSimpleName() + ".html";
            System.out.println(path);

            htmlCreator.create(path, form);

        }

        return true;
    }
}