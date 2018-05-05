package com.sochware.e_agrovet.pojo;

public class Contacts
{
    private String name;

    private String Latitude;

    private String Longitude;

    private String add;

    private String org;

    private String contact;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLatitude ()
    {
        return Latitude;
    }

    public void setLatitude (String Latitude)
    {
        this.Latitude = Latitude;
    }

    public String getLongitude ()
    {
        return Longitude;
    }

    public void setLongitude (String Longitude)
    {
        this.Longitude = Longitude;
    }

    public String getAdd ()
    {
        return add;
    }

    public void setAdd (String add)
    {
        this.add = add;
    }

    public String getOrg ()
    {
        return org;
    }

    public void setOrg (String org)
    {
        this.org = org;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", Latitude = "+Latitude+", Longitude = "+Longitude+", add = "+add+", org = "+org+", contact = "+contact+"]";
    }
}
			
			