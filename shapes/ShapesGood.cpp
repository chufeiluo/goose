/*
 * ShapesGood.cpp
 *
 *  Created on: Nov 27, 2017
 *      Author: Chu Fei Luo
 */

#include "ShapesGood.h"

using namespace std;

Shape::Shape(){}
Shape::Shape(int l, int w, string line) : length(l), width(w), lineColour(line){}
Shape::~Shape(){}

void Shape::draw() const {cout << "\nDrawing a shape.";}

void Shape::setWidth(const int& w) {width = w;}
void Shape::setLength(const int& l) {length = l;}
void Shape::setLineCol(const string& line) {lineColour = line;}

int Shape::getWidth() const {return width;}
int Shape::getLength() const {return length;}
string Shape::getLineCol() const {return lineColour;}


Fillable::Fillable() {}
Fillable::~Fillable() {}
Fillable::Fillable(const int l, const int w, const string border, const string fill){
	setLength(l);
	setWidth(w);
	setLineCol(border);
	fillColour = fill;
}
void Fillable::setFillCol(const string& fill) { fillColour = fill; }
string Fillable::getFillCol() const { return fillColour; }


Text::Text(){}
Text::~Text() {}
Text::Text(const int l, const int w, const string line, const string tex) : text(tex){
	setLength(l);
	setWidth(w);
	setLineCol(line);
}
void Text::setText(const string& tex) { text = tex;}
string Text::getText() const { return text; }


Square::~Square(){}
void Square::draw() const {
	cout << "\nDrawing a " << getLineCol() << " square.";
}


FilledSquare::~FilledSquare(){}
void FilledSquare::draw() const{
	cout << "\nDrawing a " << getLineCol() << " square."<< " With " << getFillCol() << " fill.";
}


FilledTextSquare::FilledTextSquare(const int l, const int w, const string line, const string fill, const string text){
	setLength(l);
	setWidth(w);
	setLineCol(line);
	setFillCol(fill);
	setText(text);
}
FilledTextSquare::~FilledTextSquare(){}
void FilledTextSquare::draw() const{
	cout << "\nDrawing a " << getLineCol() << " square."<< " With " << getFillCol() << " fill." << " Containing the text: \"" << getText() << "\".";
}


Circle::~Circle(){}
void Circle::draw() const{
	cout << "\nDrawing a " + getLineCol() + " circle.";
}


FilledCircle::~FilledCircle(){}
void FilledCircle::draw() const{
	cout << "\nDrawing a " << getLineCol() << " circle." << " With " << getFillCol() << " fill.";
}

Arc::~Arc(){}
void Arc::draw() const{
	cout << "\nDrawing a " << getLineCol() << " arc.";

}





