package Query_Builder;

public interface InternalQuery {
    public Query getDetailByKey();
    public Query deleteByKey();
    public Query updateDetailByKey();
    public Query insertDetail();
}
