package graal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class App {

	public static void main(String... args) throws IOException {
		
		InputStreamReader is = new InputStreamReader(App.class.getResourceAsStream("/t.py"));
		Source source = Source.newBuilder("python", is, "t").build();
		Context c = Context.newBuilder().allowAllAccess(true).build();
		Value result = c.eval(source);
		Value r2 = c.eval("python", "instance");

		Value r5 = r2.getMember("createMessage").execute("sender:)", "hey = there");
		System.out.println(r5.asString());
		
		
		System.out.println(c.getBindings("python").getMember("myinstance"));
		Value gg = c.getPolyglotBindings();
		System.out.println(gg.getMember("myinstance").getMember("hello").execute(args));
	}
}
