# File:   nhpn.py
# Author: Punyashloka Biswal <punya@mit.edu>
# Date:   04/09/2007
# Modified: Michael Lieberman <mathmike@mit.edu>
# Date:   04/03/2008

"""Defines Python classes corresponding to nodes and edges in the
National Highway Planning Network (NHPN) database. Also provides a
way to load such objects from files."""

class Node:
    """An NHPN geographical node."""

    def __init__(self, longitude, latitude, state, description):
        """Create an instance of a node from its longitude, latitude,
        two-character state code, and possibly a description
        string."""
        self.longitude = longitude
        self.latitude = latitude
        self.state = state
        self.description = description

    def __repr__(self):
        """Convert to string for printing."""
        return "Node(%s, %s, '%s', '%s')" % (self.longitude, self.latitude,
                                             self.state, self.description)


class Link:
    """A bi-directional edge linking two NHPN nodes."""
    
    def __init__(self, begin, end, description):
        """Create a link given its beginning and end (which must be nodes) and
        possibly a description string."""
        self.begin = begin
        self.end = end
        self.description = description

    def __repr__(self):
        """Convert to string for printing."""
        return "Link(%s, %s, '%s')" % (self.begin, self.end, self.description)


class Loader:
    """An instance of Loader can be used to access NHPN nodes and links as
    Python objects."""

    def __init__(self, nodesource='data/nhpn.nod', linksource='data/nhpn.lnk'):
        """Load node and link objects from corresponding files."""
        nodeForFeatureID = {}   # FeatureID -> node mapping

        # Load nodes and add to feature table
        self._nodes = []
        try:
            nodefile = open(nodesource, 'r')
            for line in nodefile:
                featureId = int(line[23:33])
                longitude = int(line[33:43])
                latitude = int(line[43:53])
                state = line[53:55].strip()
                description = line[55:88].strip()
                
                node = Node(longitude, latitude, state, description)
                nodeForFeatureID[featureId] = node
                self._nodes.append(node)
        finally:
            nodefile.close()

        # Load links
        links = []
        try:
            linkfile = open(linksource, 'r')
            for line in linkfile:
                begin = nodeForFeatureID[int(line[33:43])]
                end = nodeForFeatureID[int(line[43:53])]
                description = line[53:88].strip()
                links.append(Link(begin, end, description))
        finally:
            linkfile.close()
        # The following line is to not break test case 3 (otherwise unneeded).
        self._links = links

    def nodes(self):
        """List of all NHPN nodes."""
        return self._nodes

    def links(self):
        """List of all NHPN links."""
        return self._links


class Visualizer:
    """Visualizes a path (represented as a sequence of links) using Google
    Maps or Google Earth."""

    @staticmethod
    def toKML(path, filename="path.kml"):
        """Given a sequence of nodes representing a path, creates a
        KML file with that path."""
        
        kml = open(filename, mode='w')
        kml.write("""<?xml version="1.0" encoding="utf-8"?>
<kml xmlns="http://earth.google.com/kml/2.1">
  <Document>
    <Placemark>
      <LineString>
        <extrude>1</extrude>
        <tessellate>1</tessellate>
        <coordinates>
""")
        kml.writelines("%f,%f\n" % (node.longitude/1000000.,node.latitude/1000000.) 
                       for node in path)
        kml.write("""</coordinates>
      </LineString>
    </Placemark>
  </Document>
</kml>
""")
        kml.close()
