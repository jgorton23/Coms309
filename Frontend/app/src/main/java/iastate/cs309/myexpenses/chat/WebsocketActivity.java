package iastate.cs309.myexpenses.chat;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import androidx.appcompat.app.AppCompatActivity;

import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.login.LoginActivity;

public class WebsocketActivity extends AppCompatActivity {
    private WebSocketClient mWebSocketClient;

    private Button bConnect, bDisconnect, bSendButton;
    private TextView mOutput;
    private EditText mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket);

        // Get the buttons
        bConnect = findViewById(R.id.b_connect);
        bSendButton = findViewById(R.id.b_sendMessage);
        bDisconnect = findViewById(R.id.b_Disconnect);

        // Get the textview
        mOutput = findViewById(R.id.m_output);

        // Add scrolling
        mOutput.setMovementMethod(new ScrollingMovementMethod());

        //Get the editText
        mInput = findViewById(R.id.m_input);

        // Add handlers to the buttons
        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWebSocket();
            }
        });

        bDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebSocketClient.close();
                mOutput.setText("");
            }
        });

        bSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the message from the input
                String message = mInput.getText().toString();

                // If the message is not empty, send the message
                if(message != null && message.length() > 0){
                    mWebSocketClient.send(message);
                }
            }
        });

        Button back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }

    private void connectWebSocket() {
        URI uri;
        try {
            /*
             * To test the clientside without the backend, simply connect to an echo server such as:
             *  "ws://echo.websocket.org"
             */
            uri = new URI("http://coms-309-ug-02.cs.iastate.edu:8080/chat/" + LoginActivity.getUserUsername());
//             uri = new URI("ws://echo.websocket.org");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String msg) {
                Log.i("Websocket", "Message received");
                mOutput.append("\n" + msg);
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
                Log.i("Websocket", "Closed" + reason);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error" + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}

