package dao;

import wherehows.models.table.ImpactDataset;
import wherehows.models.table.LineagePathInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import utils.Lineage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpactDatasetRowMapper implements RowMapper<ImpactDataset>
{
    public static String STORAGE_TYPE_COLUMN = "storage_type";
    public static String OBJECT_NAME_COLUMN = "abstracted_object_name";
    public static String DATASET_ID_COLUMN = "id";

    @Override
    public ImpactDataset mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        String storageType = rs.getString(STORAGE_TYPE_COLUMN);
        String filePath = rs.getString(OBJECT_NAME_COLUMN);
        Long id = rs.getLong(DATASET_ID_COLUMN);

        LineagePathInfo lineagePathInfo = new LineagePathInfo();
        lineagePathInfo.storageType = storageType;
        lineagePathInfo.filePath = filePath;

        ImpactDataset impactDataset = new ImpactDataset();
        impactDataset.urn = Lineage.convertToURN(lineagePathInfo);
        impactDataset.id = id;
        if (impactDataset.id != null && impactDataset.id > 0)
        {
            impactDataset.isValidDataset = true;
            impactDataset.datasetUrl = "#/datasets/" + Long.toString(impactDataset.id);
        }
        else
        {
            impactDataset.isValidDataset = false;
        }
        if (StringUtils.isNotBlank(impactDataset.urn))
        {
            int index = impactDataset.urn.lastIndexOf('/');
            if (index != -1)
            {
                impactDataset.name = impactDataset.urn.substring(index+1);
            }
        }

        return impactDataset;
    }
}
