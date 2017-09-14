ALTER TABLE NOTE DROP FOREIGN KEY FK_NOTE_author
ALTER TABLE Text_Note DROP FOREIGN KEY FK_Text_Note_id
ALTER TABLE Audio_Note DROP FOREIGN KEY FK_Audio_Note_id
ALTER TABLE Image_Note DROP FOREIGN KEY FK_Image_Note_id
ALTER TABLE Note_Has_Tags DROP FOREIGN KEY FK_Note_Has_Tags_tag_text
ALTER TABLE Note_Has_Tags DROP FOREIGN KEY FK_Note_Has_Tags_note_id
ALTER TABLE User_Has_Shared_Notes DROP FOREIGN KEY FK_User_Has_Shared_Notes_shared_note
ALTER TABLE User_Has_Shared_Notes DROP FOREIGN KEY FK_User_Has_Shared_Notes_username
ALTER TABLE User_Has_Notes DROP FOREIGN KEY FK_User_Has_Notes_author_name
ALTER TABLE User_Has_Notes DROP FOREIGN KEY FK_User_Has_Notes_note_id
DROP TABLE NOTE
DROP TABLE User
DROP TABLE tag
DROP TABLE Text_Note
DROP TABLE Audio_Note
DROP TABLE Image_Note
DROP TABLE Note_Has_Tags
DROP TABLE User_Has_Shared_Notes
DROP TABLE User_Has_Notes
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
