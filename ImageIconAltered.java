import javax.swing.*;

public class ImageIconAltered
{
    private ImageIcon im;
    private String str;

    public ImageIconAltered(ImageIcon image, String description)
    {
        im = image;
        str = description;
    }

    public ImageIcon getImage()
    {
        return im;
    }
    
    public String getDescription()
    {
        return str;
    }

    public boolean isSameAs(ImageIconAltered tester)
    {
        if (tester.getDescription().compareTo(getDescription()) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
