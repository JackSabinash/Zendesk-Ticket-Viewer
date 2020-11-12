
public class Ticket {
  
  private String created_at;
  private String updated_at;
  private String subject;
  private String description;
  private String status;
  private Integer requester_id;
  private String[] tags;
  private Boolean has_incidents;
  
  
  public String getCreatedAt()
  {
    return created_at;
  }
  
  public void setCreatedAt(String created_at)
  {
    this.created_at = created_at;
  }
  
  public String getUpdatedAt()
  {
    return updated_at;
  }
  
  public void setUpdatedAt(String updated_at)
  {
    this.updated_at = updated_at;
  }
  
  public String getSubject()
  {
    return subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Integer getRequesterId()
  {
    return requester_id;
  }
  
  public void setRequesterId(Integer requester_id)
  {
    this.requester_id = requester_id;
  }
  
  public String[] getTags()
  {
    return tags;
  }
  
  public void setTags(String[] tags)
  {
    this.tags = tags;
  }

  public Boolean getHasIncidents()
  {
    return has_incidents;
  }
  
  public void setHasIncidents(Boolean has_incidents)
  {
    this.has_incidents = has_incidents;
  }
}
