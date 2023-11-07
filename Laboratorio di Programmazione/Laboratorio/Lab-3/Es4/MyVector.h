class MyVector{
    class Invalid{};

    public:
        MyVector(int dimension);        //Constructor
        ~MyVector();                    //Destructor

        //double operator[](int index);
        double operator[](int index) const;

        void safe_set(int position, double value);         //Add a new element in vector range
        double safe_get(int position);                     //Get an element in vector range

        int size() const;          //Return the num of vector elements

    private:
        int sz;                     //Vector size
        double *array = nullptr;    //A pointer to the first element of the array

};