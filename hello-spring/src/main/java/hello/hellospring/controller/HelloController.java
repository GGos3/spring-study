package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";
    }
    // mvc(model view controller)
    @GetMapping("hello-mvc")
    public  String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    // api
    @GetMapping("hello-string")
    @ResponseBody //ResponseBody를 사용하면 viewResolver를 사용하지 않고 body에 직접 반환함
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //String은 그대로 반환함
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체의 경우에는 JSON 형태로 반환함
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}