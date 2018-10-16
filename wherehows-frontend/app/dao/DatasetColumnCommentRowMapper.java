package dao;

import wherehows.models.table.DatasetColumnComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DatasetColumnCommentRowMapper implements RowMapper<DatasetColumnComment> {
    private static String ID_COLUMN = "id";
    private static String AUTHOR_COLUMN = "author";
    private static String TEXT_COLUMN = "text";
    private static String CREATED_TIME_COLUMN = "created";
    private static String MODIFIED_TIME_COLUMN = "modified";
    private static String FIELD_ID_COLUMN = "field_id";
    private static String IS_DEFAULT_COLUMN = "is_default";

    @Override
    public DatasetColumnComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong(ID_COLUMN);
        String author = rs.getString(AUTHOR_COLUMN);
        String text = rs.getString(TEXT_COLUMN);
        String created = rs.getString(CREATED_TIME_COLUMN);
        String modified = rs.getString(MODIFIED_TIME_COLUMN);
        Long columnId = rs.getLong(FIELD_ID_COLUMN);
        String strIsDefault = rs.getString(IS_DEFAULT_COLUMN);
        boolean isDefault = false;
        if (StringUtils.isNotBlank(strIsDefault) && strIsDefault.equals("Y")) {
            isDefault = true;
        }
        DatasetColumnComment datasetColumnComment = new DatasetColumnComment();

        datasetColumnComment.id = id;
        datasetColumnComment.author = author;
        datasetColumnComment.text = text;
        datasetColumnComment.created = created;
        datasetColumnComment.modified = modified;
        datasetColumnComment.columnId = columnId;
        datasetColumnComment.isDefault = isDefault;

        return datasetColumnComment;
    }
}
