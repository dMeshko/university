using _0_VP_GameProject.Properties;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _0_VP_GameProject
{
    public class Actor
    {
        public Image Model { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public int SquareSize { get; set; }
        public static readonly int VELOCITY = 2;
        public Actor(int x, int y, int size)
        {
            X = x;
            Y = y;
            SquareSize = size;
            Model = Resources.bird_fall;
        }

        public void Draw(Graphics g)
        {
            g.DrawImage(Model, new Rectangle(X, Y, SquareSize, SquareSize));
            
        }

        public void Move(int y)
        {
            Y += y;
        }
    }
}
