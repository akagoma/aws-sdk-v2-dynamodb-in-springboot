package com.akagoma.app.domain.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.akagoma.app.domain.Dynamo;
import com.akagoma.app.domain.model.Note;
import com.akagoma.app.domain.model.User;
import com.akagoma.app.domain.repository.NoteRepository;
import com.akagoma.app.domain.repository.UserRepository;
import software.amazon.awssdk.enhanced.dynamodb.model.TransactWriteItemsEnhancedRequest;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 無記名ノート投稿
     *
     * @param note ノート
     */
    public void addAnonymousNote(Note note) {
        // 無記名ユーザー作成
        User anonymous = new User(UUID.randomUUID().toString(), "anonymous");

        // リクエスト作成
        TransactWriteItemsEnhancedRequest request =
            Dynamo.addPutItem(Dynamo.addPutItem(null, note, Note.class), anonymous, User.class)
                .build();

        // 書き込み
        Dynamo.transactWriteItems(request);
    }
}
