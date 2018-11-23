

# helper function that checks the relationship between two tiles
def relation(tile1, tile2):
    [num1, suit1] = list(tile1)
    [num2, suit2] = list(tile2)

    if suit1 == suit2:  # same suit
        if num1 == num2:  # a pair
            return 4
        # otherwise just the same suit
        return 1

    elif (suit1 == 'D' or suit1 == 'X') or (suit2 == 'D' or suit2 == 'X'):
        # not the same suit, but also can't form a run because one is either dragon or wind
        return 0  # no relation
    else:
        if abs(int(num1) - int(num2)) == 1:  # within 1 of each other
            return 2
        elif abs(int(num1) - int(num2)) == 2:  # within 2 of each other
            return 3

    # otherwise, no relation that we care about
    return 0


# stores a set of tiles in a list, as well as a key
# tracks the number of pairs
class TileSet:
    num_pairs = 0

    def __init__(self, val):
        self.tiles = list([val])
        self.key = 0
        # key: 0 is single, 1 is the same suit, 2 is 2 in a row
        # 3 is two cards that're 2 apart, 4 is a pair
        # 5 is complete

    # places tile in the set that calls the function
    # returns 1 if tile was placed in hand, -1 otherwise
    def update(self, tile):
        r = relation(self.tiles[0], tile)  # find relation between tile and first tile in set
        if r is 0:  # no relation, no point checking other conditions
            return -1
        elif self.key is 0:  # the set has a single tile and the two have a relation
            self.tiles.append(tile)
            self.key = r
            if r is 4:
                self.num_pairs = self.num_pairs + 1
        elif self.key is 4:  # set is a pair
            if ((r == 1) or (r == 4)) and self.num_pairs > 1:  # only considers adding if more than one pair in hand
                self.tiles.append(tile)
                self.key = 5
                self.num_pairs = self.num_pairs - 1
            else:
                return -1
        elif self.key is 1:  # set is same suit
            if r == 1 or r == 4:  # tile is same suit as set
                self.tiles.append(tile)
                self.key = 5
            else:
                return -1
        elif self.key is 2:  # 2 in a row
            # check both tiles in set; if tile passed in is part of the row, add to set
            r2 = relation(self.tiles[1], tile)
            if (r == 2 and r2 == 3) or (r == 3 and r2 == 2):
                self.tiles.append(tile)
                self.key = 5
            else:
                return -1
        elif self.key is 3:  # two tiles that're 2 apart
            # check both tiles in set; if tile passed in is between the two, add to set
            r2 = relation(self.tiles[1], tile)
            if r == 2 and r2 == 2:
                self.tiles.append(tile)
                self.key = 5
            else:
                return -1

        return 1

    def __str__(self):
        return str(self.tiles)


# sorting key for TileSet
def tile_sort(tile_set):
    return tile_set.key


class Player:
    def __init__(self):
        self.hand = []  # a list of TileSets

    def __str__(self):
        return [str(h) for h in self.hand]

    # adds a tile to the hand
    # if this is winning hand, returns 2
    # if tile is added to an existing set, returns 1
    # if tile is in a set by itself, returns 0
    def add(self, tile):
        check = -1
        # look through all the current sets, try to place tile
        for i in range(len(self.hand)):
            # node already complete
            if self.hand[i].key == 5:
                if i > 3:  # 4th set is complete, means all others before it are also complete
                    # if last set is a pair, this is a winning hand
                    if self.hand[-1].key is 4:
                        return 2
                    elif relation(self.hand[-1].tiles[0], tile) is 4:  # check if the tile being added forms a pair
                        self.hand[-1].tiles.append(tile)
                        return 2
                    else:  # otherwise add to end, will be discarded in remove()
                        self.hand.append(TileSet(tile))
                        return 0
            else:
                check = self.hand[i].update(tile)
                if check is not -1:
                    break

        if check is -1:  # single tile, add it to the end
            self.hand.append(TileSet(tile))
            return 0
        else:
            self.hand = sorted(self.hand, key=tile_sort, reverse=True)
            return 1

    # removes a tile from hand
    # if winning hand, returns 2
    # if not winning hand and the removal succeeded, returns 1
    # if an error occurred, returns 0
    def remove(self):
        # if second-to-last set is complete and last set is a pair, winning hand
        if self.hand[-1].key == 4 and self.hand[-2].key == 5:
            return 2

        for i in range(len(self.hand)):
            # -i iterates through loop in reverse
            if self.hand[-i].key == 0:  # single set
                tile = self.hand.pop(-i).tiles[0]
                if self.add(tile) == 0:  # try to add it to another set; if still a single tile, remove again
                    self.hand.pop()
                    return 1
            elif 0 < self.hand[-i].key < 4:  # two-tile set, not a pair
                [tile1, tile2] = self.hand.pop(-i).tiles
                if self.add(tile1) == 0:
                    self.hand.pop()
                    self.add(tile2)
                return 1

        return 0
