package io.nirahtech.erp.edm.document;

import java.util.Set;

interface Tagable {
    Set<Tag> getTags();
    void addTags(Tag... tags);
    void deleteTags(Tag... tags);
}
