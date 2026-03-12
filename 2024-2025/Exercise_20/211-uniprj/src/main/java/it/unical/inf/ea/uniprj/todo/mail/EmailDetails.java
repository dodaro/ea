package it.unical.inf.ea.uniprj.todo.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
 
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private boolean isHtml;
}