package io.nirahtech.erp.edm.document;

import java.util.Set;

public interface Commentable {
    
    Set<Comment> getComments();
    void addComments(Comment... comments);
    void deleteComments(Comment... comments);

}
