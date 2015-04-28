using _0_VP_GameProject.Properties;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _0_VP_GameProject
{
    public partial class Form1 : Form
    {
        //public static
        public static readonly int MAX_WIDTH = 600;
        public static readonly int MAX_HEIGHT = 640;
        public static readonly int SQUARE_SIZE = 60;
        public static readonly int pipeMinHeight = 70;
        public static readonly int pipeDistanceY = 142;
        public static readonly int pipeDistanceX = 300;

        //public
        public Actor bird { get; set; }
        public List<Tunnel> topTerrain { get; set; }
        public List<Tunnel> bottomTerrain { get; set; }
        public int DownVelocity { get; set; }
        private int highScore;
        

        //private
        private Timer timer;
        private Label label;
        private int Points;
        private static readonly int FRAMES_PER_SECOND = 40;

        public Form1()
        {
            this.StartPosition = FormStartPosition.CenterScreen;
            InitializeComponent();

            DoubleBuffered = true;

            highScore = 0;
            if (File.Exists("highScore.txt"))
            {
                StreamReader fo = File.OpenText("highScore.txt");
                highScore = Convert.ToInt32(fo.ReadLine());
                fo.Close();
            }
            else
            {
                StreamWriter fi = File.CreateText("highScore.txt");
                fi.WriteLine(highScore);
                fi.Flush();
                fi.Close();
            }

            //set the heights
            this.Width = MAX_WIDTH;
            this.Height = MAX_HEIGHT;
            this.BackgroundImage = Resources.background;
            label = new Label();
            //label.Width = 10;
            label.BackColor = Color.Transparent;
            //increase the font!!
            label.Font = new Font(label.Font.FontFamily, 30);
            label.Location = new Point(MAX_WIDTH / 2, 30);
            label.ForeColor = Color.Red;
            label.Height = 50;
            this.Controls.Add(label);
            
        }

        public void newGame()
        {
            Points = 0;
            label.Text = Points.ToString();
            DownVelocity = 13;
            bird = new Actor(MAX_WIDTH / 8, MAX_HEIGHT / 3, SQUARE_SIZE);
            topTerrain = new List<Tunnel>();
            bottomTerrain = new List<Tunnel>();
            timer = new Timer();
            timer.Interval = FRAMES_PER_SECOND;
            timer.Tick += new EventHandler(timer_Tick);
            timer.Start();
            generateTerain();
        }

        public void generateTerain()
        {
            Random rnd = new Random();

            for (int i=0;i<4;i++)
            {
                int X = 500 + i*pipeDistanceX;
                int Y = rnd.Next(pipeMinHeight + pipeDistanceY, MAX_HEIGHT - (pipeMinHeight + pipeDistanceY));
                topTerrain.Add(new Tunnel(X, 0, Y, Tunnel.TunnelType.Top));
                bottomTerrain.Add(new Tunnel(X, Y + pipeDistanceY, MAX_HEIGHT - Y, Tunnel.TunnelType.Bottom));
            }
        }

        public bool gameOver()
        {
            Point birdLeftTop = new Point(bird.X, bird.Y);
            Point birdRightBottom = new Point(bird.X + SQUARE_SIZE, bird.Y + SQUARE_SIZE);

            if (birdRightBottom.Y >= MAX_HEIGHT) //check if u fell off the map
                return true;

            if (birdLeftTop.Y + SQUARE_SIZE < 0) //bitch overflew
                return true;

            //check overlap of 2 rectangles
            foreach (Tunnel t in topTerrain)
            {
                Point tunnelLeftTop = new Point(t.X, t.Y);
                Point tunnelRightBottom = new Point(t.X + t.Width, t.Y + t.Height);
                if (birdLeftTop.X < tunnelRightBottom.X && birdRightBottom.X > tunnelLeftTop.X && birdLeftTop.Y < tunnelRightBottom.Y && birdRightBottom.Y > tunnelLeftTop.Y)
                    return true;
            }

            foreach (Tunnel t in bottomTerrain)
            {
                Point tunnelLeftTop = new Point(t.X, t.Y);
                Point tunnelRightBottom = new Point(t.X + t.Width, t.Y + t.Height);
                if (birdLeftTop.X < tunnelRightBottom.X && birdRightBottom.X > tunnelLeftTop.X && birdLeftTop.Y < tunnelRightBottom.Y && birdRightBottom.Y > tunnelLeftTop.Y)
                    return true;
            }

            return false;
        }

        private bool gotPoint(Tunnel t)
        {
            return ((bird.X + SQUARE_SIZE >= t.X + t.Width / 2) && (bird.X + SQUARE_SIZE <= t.X + t.Width));
        }

        void timer_Tick(object sender, EventArgs e)
        {
             if (gameOver())
            {
                timer.Stop();
                
                 string tmpContent;

                 tmpContent = Points > highScore ? ("Wow..Impresive Game..\nNew HighScore!!\nYour score: " + Points + ", old HighScore = " + highScore + "\nWould you like to play 1 more game?") : ("Meh...noob\nYour score = " + Points + " HighScore = " + highScore + "\nWould you like to play 1 more game?"); 

                 //manage scrores
                if (Points > highScore)
                {
                    highScore = Points;
                    StreamWriter fo = new StreamWriter("highScore.txt", false);
                    fo.WriteLine(highScore);
                    fo.Flush();
                    fo.Close();
                }

                //da dialog..
                DialogResult d = MessageBox.Show(tmpContent, "Game over", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (d == System.Windows.Forms.DialogResult.Yes)
                    newGame();
                else
                    Close();
            }
            DownVelocity += Actor.VELOCITY;
            bird.Move(DownVelocity);
            foreach (Tunnel t in topTerrain)
            {
                t.Move(9);
                if (gotPoint(t) && !t.pointCounted)
                {
                    Points++;
                    label.Text = Points.ToString();
                    t.pointCounted = true;
                }
            }
                
            foreach (Tunnel t in bottomTerrain)
                t.Move(9);

            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            bird.Draw(e.Graphics);
            foreach (Tunnel t in topTerrain)
            {
                t.Draw(e.Graphics);
            }
            foreach (Tunnel t in bottomTerrain)
            {
                t.Draw(e.Graphics);
            }
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Space)
            {
                DownVelocity = -15;
                bird.Model = Resources.bird_fly;
            }
                
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            newGame();
        }

        private void Form1_KeyUp(object sender, KeyEventArgs e)
        {
            bird.Model = Resources.bird_fall;
        }
    }
}
