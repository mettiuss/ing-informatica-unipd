class MyVector{
    class Invalid{};

    public:
        MyVector(int dimension);
        ~MyVector();

        double& operator[](int index);
        double operator[](int index) const;

        void safe_set(int index, double value);
        double safe_get(int index) const;
        
        double at(int index) const;
        double& at(int index);

        void push_back(double value);
        double pop_back();

        void reserve(int n);

        int size() const { return sz; };
    private:
        double *array = nullptr;
        int true_sz;
        int reserved;
        int sz;
        void resize(int dim);
	    double& safe_get_reference(int position);
};
