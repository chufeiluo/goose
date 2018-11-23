/*
 * ShapesGood.h
 *
 *  Created on: Nov 27, 2017
 *      Author: Chu Fei Luo
 */

#ifndef SHAPESGOOD_H_
#define SHAPESGOOD_H_

#include <iostream>
using namespace std;


class Shape { //base class
public:
	Shape();
	Shape(int, int, string);
	virtual ~Shape();

	virtual void draw() const;

	void setWidth(const int&);
	void setLength(const int&);
	void setLineCol(const string&);

	int getWidth() const;
	int getLength() const;
	string getLineCol() const;

private:
	int length;
	int width;
	string lineColour;
};

class Fillable : virtual public Shape { //used if shape has a fill colour
public:
	Fillable();
	~Fillable();
	Fillable(const int, const int, const string, const string);

	void setFillCol(const string&);
	string getFillCol() const;
private:
	string fillColour;
};

class Text : virtual public Shape {
public:
	Text();
	~Text();
	Text(const int, const int, const string, const string);
	void setText(const string&);
	string getText() const;
private:
	string text;
};
class Square : public Shape {
public:
	using Shape::Shape;
	~Square();

	virtual void draw() const;
};

class FilledSquare : public Fillable {
public:
	using Fillable::Fillable;
	~FilledSquare();

	virtual void draw() const;
};

class FilledTextSquare : public Fillable, public Text {
public:
	FilledTextSquare(const int, const int, const string, const string, const string);
	~FilledTextSquare();

	virtual void draw() const;
};

class Circle : public Shape {
public:
	using Shape::Shape;
	~Circle();

	virtual void draw() const;
};

class FilledCircle : public Fillable {
public:
	using Fillable::Fillable;
	~FilledCircle();

	virtual void draw() const;
};

class Arc : public Shape {
public:
	using Shape::Shape;
	~Arc();

	virtual void draw() const;
};

#endif /* SHAPESGOOD_H_ */
