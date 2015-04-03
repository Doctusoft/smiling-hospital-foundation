package ds.smiling.visitation;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(VisitationDAOImpl.class)
public interface VisitationDAO extends BaseDAO<Visitation> {

}
