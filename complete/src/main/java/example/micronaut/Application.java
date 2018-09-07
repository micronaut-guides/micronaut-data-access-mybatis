package example.micronaut;

import io.micronaut.aop.Adapter;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

    private DataSource dataSource;

    public Application(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @Adapter(ApplicationEventListener.class)
    void onStartup(StartupEvent event) {
        try {
            Connection conn = dataSource.getConnection();
            Reader reader = Resources.getResourceAsReader("schema.sql");

            ScriptRunner runner = new ScriptRunner(conn);
            runner.setErrorLogWriter(null);
            runner.runScript(reader);
            conn.commit();
            reader.close();
        } catch (IOException e) {
            // schema.sql doesn't exit. Skip execution.
        } catch (SQLException e) {
            // Ignore. If there is a problem getting DB connection the application doesn't start and this listener
            // is never executed
        }
    }
}